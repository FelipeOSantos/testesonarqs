pipeline {
    agent any
    options {
        disableConcurrentBuilds()
    }
    parameters {
        string(name: 'CUSTOM_PATH', defaultValue: 'poc', description: 'Caminho do projeto a ser analisado')
    }
    stages {
    	stage('Checkout') {
    		checkout scm
    	}
        stage('Inicio') {
            steps {
                echo 'Hello World'
            }
        }
    }
}