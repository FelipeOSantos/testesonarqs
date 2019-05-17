pipeline {
    agent any
    options {
        disableConcurrentBuilds()
    }
    parameters {
        string(name: 'CUSTOM_PATH', defaultValue: 'poc', description: 'Caminho do projeto a ser analisado')
    }
    stages {
        stage('Inicio') {
            steps {
                echo 'Hello World'
            }
        }
    }
}