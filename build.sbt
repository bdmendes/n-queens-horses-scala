import Dependencies._

ThisBuild / scalaVersion := "3.3.3"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "solution"

lazy val root = (project in file("."))
  .settings(
    name := "n-queens-leetcode",
    libraryDependencies += munit % Test
  )
