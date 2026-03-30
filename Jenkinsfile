pipeline {
    agent any

    stages {
        stage('Build Images') {
            steps {
                sh "docker compose -f docker-compose.prod.yml build --pull"
            }
        }

        stage('Deploy') {
            steps {
                sh "docker compose -f docker-compose.prod.yml up -d"
            }
        }

        stage('Health Check') {
            steps {
                sh 'sleep 15 && curl -f http://localhost || (echo "App not reachable!" && exit 1)'
            }
        }
    }

    post {
        always {
            sh "docker image prune -f || true"
        }
    }
}
