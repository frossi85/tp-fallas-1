package dummy

import dummy.model._
import java.io.FileInputStream
import java.io.InputStreamReader
import org.drools.core.audit.WorkingMemoryConsoleLogger
import org.drools.io.Resource
import org.drools.io.ResourceFactory
import org.drools.builder.KnowledgeBuilderFactory
import org.drools.builder.ResourceType
import java.io.File
import collection.JavaConversions._
import org.slf4j.LoggerFactory

object Dummy {
  val logger = LoggerFactory.getLogger(Dummy.getClass())

  def main(args: Array[String]) {
    logger.warn("# test me through test cases...")
    logger.warn("run 'sbt test'")
  }

  def using[R, T <% { def dispose() }](getres: => T)(doit: T => R): R = {
    val res = getres
    try doit(res) finally res.dispose
  }

  def analyze(model: List[Any], kb: String) = {
    System.setProperty("drools.dialect.java.compiler", "JANINO")

    val config = KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration()
    config.setProperty("drools.dialect.mvel.strict", "false")
    val kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(config)

    val res = ResourceFactory.newClassPathResource(kb)
    kbuilder.add(res, ResourceType.DRL)

    val errors = kbuilder.getErrors();
    if (errors.size() > 0) {
      for (error <- errors) logger.error(error.getMessage())
      throw new IllegalArgumentException("Problem with the Knowledge base");
    }


    val kbase = kbuilder.newKnowledgeBase()
    
    val results = using(kbase.newStatefulKnowledgeSession()) { session =>
      session.setGlobal("logger", LoggerFactory.getLogger(kb))
      model.foreach(session.insert(_))
      session.fireAllRules()
      session.getObjects()
    }

    results
  }

}
