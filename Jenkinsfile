pipeline {
    agent any

    environment {
        GITHUB_TOKEN = credentials('github-token') 
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn clean install test'
            }
        }

        stage('JaCoCo Coverage') {
            steps {
                sh 'mvn jacoco:prepare-agent test jacoco:report'
                sh 'mvn jacoco:check'
            }
        }
    }

    post {
        success {
            script {
                updateGitHubStatus('SUCCESS', 'Build and coverage passed')
            }
        }

        failure {
            script {
                updateGitHubStatus('FAILURE', 'Build or coverage failed')
            }
        }
    }
}

def updateGitHubStatus(String state, String description) {
    def context = "continuous-integration/jenkins"
    def commitSha = env.GIT_COMMIT

    withCredentials([usernamePassword(credentialsId: 'github-token', usernameVariable: 'carloslopllo', passwordVariable: 'GITHUB_TOKEN')]) {
        def apiUrl = "https://api.github.com/repos/carloslopllo/PROF-2021-EX1/statuses/${commitSha}"
        def data = """
        {
            "state": "${state}",
            "description": "${description}",
            "context": "${context}"
        }
        """
        sh "curl -X POST -H 'Authorization: token ${GITHUB_TOKEN}' -d '${data}' ${apiUrl}"
    }
}