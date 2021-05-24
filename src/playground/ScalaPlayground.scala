package playground

object ScalaPlayground extends App {
  println("Hello Scala!")


  /* val donorsNDF = getDataframe("donor", dfList)
   val familyRelationshipNDF = getDataframe("familyrelationship", dfList)
   val familyHistoryNDF = getDataframe("familyhistory", dfList)
   val exposureNDF = getDataframe("exposure", dfList)
   val diagnosisNDF = getDataframe("diagnosis", dfList)
   val treatmentNDF = getDataframe("treatment", dfList)
   val followUpNDF = getDataframe("followup", dfList)
   val phenotypeNDF = getDataframe("phenotype", dfList)
   val fileNDF = getDataframe("file", dfList)
   val biospecimenNDF = getDataframe("biospecimen", dfList)
   val sampleNDF = getDataframe("sampleregistration", dfList)*/
  def toEnv(t: String) = {
    val prefix = t.toUpperCase
    s"${prefix}_PREFIX=${prefix.substring(0, 3)}\n${prefix}_SEQ=${t}_seq\n${prefix}_PAD=7\n${prefix}_PAD_CHAR=0"
  }

  var all = List("study", "data_access", "donor", "family_relationship", "family_history", "exposure", "diagnosis", "treatment", "follow_up", "phenotype", "file", "biospecimen", "sample_registration");
  all.sorted.map(toEnv).foreach(println)
}
