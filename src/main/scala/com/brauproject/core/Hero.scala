package com.brauproject.core

case class Hero(id: String,
                name: String,
                skills: Seq[Skill],
                role: Role)
