package com.brauproject.core

case class Quest(name: String,
                 text: String,
                 roles: Seq[Role],
                 phases: Seq[Role],
                 tasklists:Seq[TaskList],
                 tasks:Seq[Task],
                 skills: Seq[Skill])
