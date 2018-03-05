package com.brauproject.hero

import akka.actor.{Actor, ActorLogging, Props}
import com.brauproject.skills.Skill
import com.brauproject.task.Task

import scala.annotation.tailrec

class heroActor extends Actor with ActorLogging{
  var heroSkills: Seq[Skill] = Seq[Skill]()

  override def preStart(): Unit = log.info("Hero actor started")
  override def postStop(): Unit = log.info("Hero actor stopped")

  override def receive: Receive = {
    case skill @ Skill(name) => addSkill(name)
    case task @ Task(_, _, skills) => {

    }
  }

  def addSkill(skilllName: String): Unit = {
    //This is not ideal it is here just for testing, the ideal is creating a
    // new object with the changes and delete the old one
    log.info("Adding skill {} to the Hero", skilllName)
    heroSkills = heroSkills ++ Seq(Skill(skilllName))
    log.info("Added skill to the hero")
  }

  @tailrec
  final def checkSkillsExpectations(skillList: Seq[Skill]): Boolean = {
    skillList match {
      case Nil => true
      case head :: tail =>
        log.info("Checking if skill {} match the Hero Skills", head)
        if (heroSkills.contains(head)) {
          checkSkillsExpectations(tail)
        } else {
          log.info("Skill {} does not match with Hero Skills", head)
          false
        }
    }
  }
}
