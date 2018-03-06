package com.brauproject.skills

import com.brauproject.core.Skill

object skillDatabase {
  def getSkillsByHero(heroName: String): Seq[Skill] = ???


  def setSkill(heroId: String, skill: Skill): Unit = ???
}
