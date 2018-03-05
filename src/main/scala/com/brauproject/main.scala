package com.brauproject

import akka.actor.{ActorSystem, Props}
import com.brauproject.hero.heroActor
import com.brauproject.skills.Skill
import com.brauproject.task.Task

object main extends App {

  val system = ActorSystem("testSystem")

  // Supervising with Fail
  val hero = system.actorOf(Props[heroActor])
  hero ! Skill("python")
  hero ! Task("Nice Task", "Do Something in python", Seq(Skill("python")))
  hero ! Task("Bad Task", "This task you don't know", Seq(Skill("javascript"), Skill("python")))

}
