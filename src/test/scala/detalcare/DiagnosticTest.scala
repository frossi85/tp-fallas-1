package detalcare

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.ShouldMatchers
import org.scalatest.junit.JUnitRunner
import collection.JavaConversions._
import model._

@RunWith(classOf[JUnitRunner])
class DiagnosticTest extends FunSuite with ShouldMatchers {


  def model1 = {
    val toothWithPulpNecrosis = ToothTest(
      Coloration.brown,
      List(KindOfPain.intense, KindOfPain.acute, KindOfPain.located),
      ColdStimulus.negative,
      HeatStimulus.negative,
      ElectricalStimulation.negative,
      PercussionStimulation.positive,
      PulpState.shattered,
      PatientAge.oldMan
    )

    val toothWithPulpHyperemia = ToothTest(
      Coloration.normal,
      List(KindOfPain.intense, KindOfPain.located),
      ColdStimulus.positive,
      HeatStimulus.positive,
      ElectricalStimulation.positive,
      PercussionStimulation.negative,
      PulpState.intact,
      PatientAge.young
    )

    val toothWithHyperplasticPulpitis = ToothTest(
      Coloration.reddish,
      List(KindOfPain.acute, KindOfPain.located),
      ColdStimulus.positive,
      HeatStimulus.negative,
      ElectricalStimulation.negative,
      PercussionStimulation.positive,
      PulpState.hypertrophied,
      PatientAge.young
    )

    val toothWithPulpAtrophy = ToothTest(
      Coloration.yellowish,
      List(KindOfPain.noPain),
      ColdStimulus.negative,
      HeatStimulus.negative,
      ElectricalStimulation.negative,
      PercussionStimulation.negative,
      PulpState.atrophied,
      PatientAge.oldMan
    )

    val toothWithPulpitisSerosa = ToothTest(
      Coloration.normal,
      List(KindOfPain.spontaneous, KindOfPain.diffuse, KindOfPain.discontinuous),
      ColdStimulus.positive,
      HeatStimulus.positive,
      ElectricalStimulation.positive,
      PercussionStimulation.positive,
      PulpState.intact,
      PatientAge.oldMan
    )

    val toothWithAcutePurulentPulpitis = ToothTest(
      Coloration.normal,
      List(KindOfPain.spontaneous, KindOfPain.intense, KindOfPain.continuous, KindOfPain.located),
      ColdStimulus.negative,
      HeatStimulus.positive,
      ElectricalStimulation.positive,
      PercussionStimulation.positive,
      PulpState.partiallyDestroyed,
      PatientAge.young
    )

    val toothWithInfiltrativePulpitis = ToothTest(
      Coloration.normal,
      List(KindOfPain.spontaneous, KindOfPain.continuous, KindOfPain.located),
      ColdStimulus.positive,
      HeatStimulus.positive,
      ElectricalStimulation.positive,
      PercussionStimulation.positive,
      PulpState.shattered,
      PatientAge.young
    )

    val toothWithoutDiagnosis = ToothTest(
      Coloration.normal,
      List(KindOfPain.noPain),
      ColdStimulus.negative,
      HeatStimulus.negative,
      ElectricalStimulation.negative,
      PercussionStimulation.negative,
      PulpState.intact,
      PatientAge.young
    )

    List(
      toothWithPulpNecrosis,
      toothWithPulpHyperemia,
      toothWithHyperplasticPulpitis,
      toothWithPulpAtrophy,
      toothWithPulpitisSerosa,
      toothWithAcutePurulentPulpitis,
      toothWithInfiltrativePulpitis,
      toothWithoutDiagnosis
    )
  }

  test("fired up test") {
    val ruleEngine = new RuleEngine
    val found = ruleEngine.analyze(model1, "KB-ToothDiagnosis.drl")
    val all = found collect { case x: Diagnosis => x}


    info(all.toList.length.toString)

    all.foreach{i=> info(i.toString)}
  }

}
