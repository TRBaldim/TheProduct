package com.brauproject.database

import com.brauproject.core.{Hero, HeroActor, Role, Skill}
import com.brauproject.exceptions.HeroNotFoundException

object HeroDatabase {
  def sinkHero(hero: HeroActor): Boolean = {
    true
  }
  def getHeroById(id: String): Hero = {
    if (id == "1234")
      Hero("1234", "Josias", Seq(Skill("skill_1", "python")),
        Role("role_1", "Pythonista", "Programa em Python", Seq(Skill("skill_1", "python"))))
    else {
      throw HeroNotFoundException("Hero with id " + id + " not found")
    }
  }
}
