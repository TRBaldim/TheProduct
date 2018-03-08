package com.brauproject

import akka.actor.{ActorSystem, Props}
import com.brauproject.core.{HeroActor, Skill, Task, ClanActor, Clan}

object main extends App {

  val system = ActorSystem("testSystem")

  // Supervising with Fail
  val dude = system.actorOf(HeroActor.getHero("1234"))
  dude ! Skill("skill_1", "python")
  dude ! Task("Nice Task", "Do Something in python", Seq(Skill("skill_1", "python")))
  dude ! Task("Bad Task", "This task you don't know", Seq(Skill("skill_1", "python"), Skill("skill_2", "javascript")))

  // Supervising with Fail
  val clan_dude = system.actorOf(ClanActor.getClan("1234"))
  dude ! Skill("skill_1", "python")
  dude ! Task("Nice Task", "Do Something in python", Seq(Skill("skill_1", "python")))
  dude ! Task("Bad Task", "This task you don't know", Seq(Skill("skill_1", "python"), Skill("skill_2", "javascript")))

  //Need to add supervisor handling
  val myMan = system.actorOf(HeroActor.getHero("666"))
  myMan ! Skill("skill_1", "python")
}
