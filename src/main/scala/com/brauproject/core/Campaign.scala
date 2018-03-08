package com.brauproject.core

case class Campaign(name: String,
                    text: String,
                    roles: Seq[Role],
                    quests:Seq[Quest],
                    skills: Seq[Skill])
