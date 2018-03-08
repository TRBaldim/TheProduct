package com.brauproject.core

case class TaskList(title: String,
                    text: String,
                    skills: Seq[Skill],
                    tasks: Seq[Task])
