package com.brauproject.skills

object skillDatabase {
  def getSkillsByHero(heroName: String): List[Skill] = {
    //Dummy return for testing
    List(Skill("python"), Skill("scala"))
  }
}
