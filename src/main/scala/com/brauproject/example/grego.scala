package com.brauproject.example

import akka.actor.{ActorSystem, Props}
import com.brauproject.example.main.system

object grego extends App {
  val system = ActorSystem("gregoSystem")

  val taskRef = system.actorOf(Props[TaskActor], "task-actor")
  println(s"Starting $taskRef")
  taskRef ! "createit"
  taskRef ! "updateit"
  taskRef ! "deleteit"

  system.terminate()
}
