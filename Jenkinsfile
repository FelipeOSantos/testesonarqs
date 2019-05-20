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
		                    error "Pipeline aborted due to quality gate failure: ${qg.status}"
							rocketSend
							    attachments: [
							        [$class: 'MessageAttachment', color: 'red', text: 'Something failed', title: 'my other attachment']
							    ],
							    channel: 'general', message: 'My message', rawMessage: true
		                }
		            }
        		}
        	}
        }
    }
}