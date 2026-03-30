pipeline {
    agent any

    environment {
        APP_DIR = '/opt/smartstudy'
    }

    stages {
        stage('Pull Latest Code') {
            steps {
                sh "cd ${APP_DIR} && git pull origin main"
            }
        }

        stage('Build Images') {
            steps {
                sh "cd ${APP_DIR} && docker compose -f docker-compose.prod.yml build --pull"
            }
        }

        stage('Deploy') {
            steps {
                sh "cd ${APP_DIR} && docker compose -f docker-compose.prod.yml up -d"
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
            sh "cd ${APP_DIR} && docker image prune -f || true"
        }
    }
}
