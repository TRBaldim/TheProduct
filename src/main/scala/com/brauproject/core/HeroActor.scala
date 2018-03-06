package com.brauproject.core

import akka.actor.{Actor, ActorLogging, Props}
import com.brauproject.database.HeroDatabase
import com.brauproject.skills.skillDatabase

import scala.annotation.tailrec

object HeroActor{
  def props(hero: Hero): Props = Props(new HeroActor(hero))

  def getHero(heroId: String): Props = {
    props(HeroDatabase.getHeroById(heroId))
  }
}

//I really didn't like to use this var here... Need to think in a better functional way...
class HeroActor(var hero: Hero) extends Actor with ActorLogging{

  override def preStart(): Unit = log.info("Hero actor {} started", hero.name)
  override def postStop(): Unit = log.info("Hero actor {} stopped", hero.name)

  override def receive: Receive = {
    case skill: Skill => addSkill(skill)
    case task @ Task(_, _, skills) =>
      if (checkSkillsExpectations(skills)) {
        log.info("Skill checking passed")
      }
  }

  def addSkill(skill: Skill): Unit = {
    //This is not ideal it is here just for testing, the ideal is creating a
    // new object with the changes and delete the old one
    log.info("Adding skill {} to the Hero", skill.name)
    hero = Hero(hero.id, hero.name, hero.skills :+ skill, hero.role)
    log.info("Added skill to the hero")
  }

  final def sinkHero(): Unit = {
    ???
  }

  @tailrec
  final def checkSkillsExpectations(skillList: Seq[Skill]): Boolean = {
    skillList match {
      case Nil =>
        log.info("All skills match the expectation")
        true
      case head :: tail =>
        log.info("Checking if skill {} match the Hero Skills", head)
        if (hero.skills.contains(head)) {
          checkSkillsExpectations(tail)
        } else {
          log.info("Skill {} does not match with Hero Skills", head)
          false
        }
    }
  }
}
