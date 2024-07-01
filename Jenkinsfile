pipeline {
    agent any
    stages {  
        stage('SCM') {
            steps {
                git branch: 'main', url: 'https://github.com/alepaco-maton/API-de-Gestion-de-Clientes.git'
            }
        } 
        stage('Build & Test') { 
            steps {
                powershell 'mvn -B  clean install'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'  
                }
                
            }

        }
        stage('dependencyCheck') {
            steps {
                dependencyCheck additionalArguments: '''--format XML --format HTML''', odcInstallation: 'owasp'
                dependencyCheckPublisher pattern: 'dependency-check-report.xml'
            }
        }
        stage('reporte de covertura') {
            steps {
                jacoco()
                publishCoverage adapters: [jacocoAdapter('target/site/jacoco/jacoco.xml')], checksName: '', sourceFileResolver: sourceFiles('NEVER_STORE')
            }
        }  
        stage("SonarQube analysis") { 
            agent any
            steps { 
              withSonarQubeEnv(installationName: 'sonarqubelocal', credentialsId: 'sonarqubecredentail') {
                powershell 'xcopy  C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\ApiGestionClientesV3  . /E /I /Y'
                powershell 'mvn sonar:sonar -Dsonar.projectName=API-de-Gestion-de-Clientes''
              }
            }
        }
        
        stage('Build Docker Image') {
            steps {
                powershell 'docker build -t api_de_gestion_de_clientes .'
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                powershell 'kubectl apply -f app.yaml'
                powershell 'kubectl apply -f app_autoscaling.yaml'
            }
        }

        stage('Test integration') {
            steps {
                powershell 'mvn integration-test'
            }
        }

    } 
}