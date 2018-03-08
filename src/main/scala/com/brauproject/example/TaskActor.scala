package com.brauproject.example

import akka.actor.{Actor, Props}

class TaskActor extends Actor {
  override def receive: Receive = {
    case "createit" =>
      val createdRef = context.actorOf(Props.empty, "database-actor")
      println(s"Created the task: $createdRef")

    case "updateit" =>
      val updatedRef = context.actorOf(Props.empty, "database-actor")
      println(s"Updated the task: $updatedRef")

    case "deleteit" =>
      val deletedRef = context.actorOf(Props.empty, "database-actor")
      println(s"Deleted the task: $deletedRef")
  }
}
