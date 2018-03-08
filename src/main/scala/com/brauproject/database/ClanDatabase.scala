package com.brauproject.database

import com.brauproject.core.{Clan, ClanActor, Role, Skill}
import com.brauproject.exceptions.ClanNotFoundException

object ClanDatabase {
  // Verifica se o clan existe?
  def sinkClan(clan: ClanActor): Boolean = {
    true
  }
  def getClanById(id: String): Clan = {
    if (id == "1234")
      Clan("1234", "TheProject","The description", Seq(Skill("skill_1", "python")))
    else {
      throw ClanNotFoundException("Clan with id " + id + " not found")
    }
  }
}
