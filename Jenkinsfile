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
                withSonarQubeEnv('SonarLocal') {
                    withMaven(maven: 'maven_3.6') {
                        sh "mvn clean package -f ${params.PATH_PROJETO} sonar:sonar -Dsonar.host.url=${SONAR_HOST_URL} -Dsonar.branch=${env.GIT_BRANCH}"
                    }
                }
            }
        }
        stage("Quality Gate"){
        	steps {
        		script {
        			timeout(5) {
		                def qg = waitForQualityGate()
		                if (qg.status != 'OK') {
		                	sh """curl -X POST -H 'Content-Type: application/json' --data '{"text":"Build ${currentBuild.displayName} for Job [${env.JOB_NAME} ${env.GIT_BRANCH}] analyzed. See ${env.BUILD_URL}. Build status: ${currentBuild.currentResult}","attachments":[{"title":"Quality gate status","text":"${qg.status}","color":"red"}]}' http://10.130.214.117:3300/hooks/ZbS27zr2S27rZjdod/LAyWdR2hBXzwviBoD7c4sJKsZ4NP8bibmFMHkHaKPayjrAiz"""
		                    error "Pipeline aborted due to quality gate failure: ${qg.status}"
		                }
		            }
        		}
        	}
        }
    }
}