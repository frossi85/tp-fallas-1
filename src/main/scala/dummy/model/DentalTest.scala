package dummy.model

import java.util.List
import java.util.{List=>JList,Map=>JMap}
import collection.JavaConversions._


case class ToothTest(
                      coloration: Coloration,
                      kindOfPains: JList[KindOfPain],
                      coldStimulus: ColdStimulus,
                      heatStimulus: HeatStimulus,
                      electricalStimulation: ElectricalStimulation,
                      percussionStimulation: PercussionStimulation,
                      pulpState: PulpState,
                      patientAge: PatientAge
                    )

case class Coloration(name:String)

object Coloration {
  val gray = Coloration("gray")
  val brown = Coloration("brown")
  val normal = Coloration("normal")
  val reddish = Coloration("reddish")
  val yellowish = Coloration("yellowish")
}




case class KindOfPain(name:String)

object KindOfPain {
  val intense = KindOfPain("intense")
  val acute = KindOfPain("acute")
  val located = KindOfPain("located")
  val noPain = KindOfPain("noPain")
  val diffuse = KindOfPain("diffuse")
  val spontaneous = KindOfPain("spontaneous")
  val discontinuous = KindOfPain("discontinuous")
  val continuous = KindOfPain("continuous")
}



case class ColdStimulus(name:String)

object ColdStimulus {
  val positive = ColdStimulus("positive")
  val negative = ColdStimulus("negative")
}

case class HeatStimulus(name:String)

object HeatStimulus {
  val positive = HeatStimulus("positive")
  val negative = HeatStimulus("negative")
}


case class ElectricalStimulation(name:String)

object ElectricalStimulation {
  val positive = ElectricalStimulation("positive")
  val negative = ElectricalStimulation("negative")
}

case class PercussionStimulation(name:String)

object PercussionStimulation {
  val positive = PercussionStimulation("positive")
  val negative = PercussionStimulation("negative")
}


case class PulpState(name:String)

object PulpState {
  val shattered = PulpState("shattered")
  val hypertrophied = PulpState("hypertrophied")
  val atrophied = PulpState("atrophied")
  val partiallyDestroyed = PulpState("partiallyDestroyed")
  val intact = PulpState("intact")
}


case class PatientAge(name:String)

object PatientAge {
  val young = PatientAge("young")
  val oldMan = PatientAge("oldMan")
}









