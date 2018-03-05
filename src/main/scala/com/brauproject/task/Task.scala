package com.brauproject.task

import com.brauproject.skills.Skill

case class Task(title: String,
                text: String,
                skills: Seq[Skill])
