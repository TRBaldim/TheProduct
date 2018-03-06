package com.brauproject.core

case class Role(id: String,
                name: String,
                description: String,
                skills: Seq[Skill])
