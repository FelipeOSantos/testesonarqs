pipeline {
    agent any
    options {
        disableConcurrentBuilds()
    }
    parameters {
        string(name: 'PATH_PROJETO', defaultValue: 'poc/testesonarqs', description: 'Path do Projeto a ser analisado')
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Analise Sonar') {
            steps {

                    withMaven(maven: 'maven_3.6') {
                        sh "mvn -f ${params.PATH_PROJETO} sonar:sonar -Dsonar.branch=${env.GIT_BRANCH}"
                    }

            }
        }

    }
}