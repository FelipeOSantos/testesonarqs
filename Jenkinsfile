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
                        echo "${env.POM_ARTIFACTID}"
                    }
                }
            }
        }
        stage("Quality Gate"){
        	steps {
        		script {
	        		sh 'env > env.txt' 
					for (String i : readFile('env.txt').split("\r?\n")) {
					    println i
					}
        			timeout(5) {
		                def qg = waitForQualityGate()
		                if (qg.status != 'OK') {
		                	sh """curl -X POST -H 'Content-Type: application/json' --data '{"text":"Project [${env.POM_ARTIFACTID} ${env.GIT_BRANCH}] analyzed. See ${qg.serverUrl}. Quality gate status: ${qg.status} ${qg.installationName}","attachments":[{"title":"Rocket.Chat","title_link":"https://rocket.chat","text":"Rocket.Chat, the best open source chat","image_url":"/images/integration-attachment-example.png","color":"#764FA5"}]}' http://10.130.214.117:3300/hooks/ZbS27zr2S27rZjdod/LAyWdR2hBXzwviBoD7c4sJKsZ4NP8bibmFMHkHaKPayjrAiz"""
		                    error "Pipeline aborted due to quality gate failure: ${qg.status}"
		                }
		            }
        		}
        	}
        }
    }
}