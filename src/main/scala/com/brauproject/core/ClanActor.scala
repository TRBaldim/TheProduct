package com.brauproject.core

import akka.actor.{Actor, ActorLogging, Props}
import com.brauproject.database.ClanDatabase
import com.brauproject.skills.skillDatabase

import scala.annotation.tailrec

// Não entendi direito a necessidade de criar esses objetos aqui
object ClanActor{
  // ClanActor recebe como propriedade um objeto do tipo Clan
  def props(clan: Clan): Props = Props(new ClanActor(clan))

  // O metódo getClan recebe um clanId como parametro e busca no banco de dados o mesmo
  def getClan(clanId: String): Props = {
    props(ClanDatabase.getClanById(ClanId))
  }
}

//I really didn't like to use this var here... Need to think in a better functional way...
class ClanActor(var clan: Clan) extends Actor with ActorLogging{

  override def preStart(): Unit = log.info("Clan actor {} started", clan.name)
  override def postStop(): Unit = log.info("Clan actor {} stopped", clan.name)

  override def receive: Receive = {
    case hero: Hero => addHero(hero)
    case task @ Task(_, _, skills) =>
      if (checkSkillsExpectations(skills)) {
        log.info("Skill checking passed")
      }
  }

  def addHero(hero: Hero): Unit = {
    log.info("Adding hero {} to the Clan", skill.name)
    clan = Clan(clan.id, clan.name, clan.text, clan.skills :+ skill)
    log.info("Added skill to the Clan")
  }

  final def sinkClan(): Unit = {
    ???
  }

  @tailrec
  final def checkSkillsExpectations(skillList: Seq[Skill]): Boolean = {
    skillList match {
      case Nil =>
        log.info("All skills match the expectation")
        true
      case head :: tail =>
        log.info("Checking if skill {} match the Clan Skills", head)
        if (clan.skills.contains(head)) {
          checkSkillsExpectations(tail)
        } else {
          log.info("Skill {} does not match with Clan Skills", head)
          false
        }
    }
  }
}
