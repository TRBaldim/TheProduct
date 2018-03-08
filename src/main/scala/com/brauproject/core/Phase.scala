package com.brauproject.core

case class Phase(name: String,
                 text: String,
                 roles: Seq[Role],
                 tasklists:Seq[TaskList],
                 tasks:Seq[Task],
                 skills: Seq[Skill])
