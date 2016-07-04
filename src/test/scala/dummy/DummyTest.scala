package dummy

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.ShouldMatchers
import org.scalatest.junit.JUnitRunner
import collection.JavaConversions._

import model._

/*
@RunWith(classOf[JUnitRunner])
class DummyTest extends FunSuite with ShouldMatchers {
  
  def model1 = {
    val martine = Someone(name="Martine", age=30, nicknames=List("titine", "titi"), attributes=Map("hairs"->"brown"))
    val martin  = Someone(name="Martin", age=40, nicknames=List("tintin", "titi"), attributes=Map("hairs"->"black"))
    val jack    = Someone(name="Jack", age=12, nicknames=List("jacquouille"), attributes=Map("eyes"->"blue"))
    val martineCar = Car(martine, "Ford", 2010, Color.blue)
    val martinCar  = Car(martin, "GM", 2010, Color.black)
    val martinCar2 = Car(martin, "Ferrari", 2012, Color.red)
    val martinCar3 = Car(martin, "Porshe", 2011, Color.red)
    
    val martinHome = Home(martin, None)
    val jackHome   = Home(jack, Some(Address("221B Baker Street", "London", "England")))
    
    List(
      martine,
      martin,
      jack, 
      martineCar,
      martinCar,
      martinCar2,
      martinCar3,
      martinHome,
      jackHome
    ) 
  }
  
  test("fired up test") {
    val found = Dummy.analyze(model1, "KB-People.drl")
    val all = found collect { case x:Information => x}
    all.foreach{i=> info(i.toString)}
    
    val valuableInfos = all collect { case x:InformationRemarkable => x}
    val partialInfos = all collect { case x:InformationRequest => x}
    
    valuableInfos should have size(2)
    partialInfos should have size(2)
    
    partialInfos.map(_.someone.name) should contain("Martine")
  }
  
}
*/


@RunWith(classOf[JUnitRunner])
class DummyTest extends FunSuite with ShouldMatchers {


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
    val found = Dummy.analyze(model1, "KB-ToothDiagnosis.drl")
    val all = found collect { case x: Diagnosis => x}


    info(all.toList.length.toString)

    all.foreach{i=> info(i.toString)}

    /*
    valuableInfos should have size(2)
    partialInfos should have size(2)

    partialInfos.map(_.someone.name) should contain("Martine")
    */
  }

}
