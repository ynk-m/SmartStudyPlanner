pipeline {
    agent any

    environment {
        REGISTRY = 'ghcr.io'
        IMAGE_BACKEND = 'ghcr.io/ynk-m/smartstudy-backend'
        IMAGE_FRONTEND = 'ghcr.io/ynk-m/smartstudy-frontend'
        APP_DIR = '/opt/smartstudy'
    }

    stages {
        stage('Login to GHCR') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'ghcr-credentials',
                    usernameVariable: 'GHCR_USER',
                    passwordVariable: 'GHCR_TOKEN'
                )]) {
                    sh 'echo "$GHCR_TOKEN" | docker login ghcr.io -u "$GHCR_USER" --password-stdin'
                }
            }
        }

        stage('Pull Images') {
            steps {
                sh """
                    docker pull ${IMAGE_BACKEND}:latest
                    docker pull ${IMAGE_FRONTEND}:latest
                """
            }
        }

        stage('Deploy') {
            steps {
                sh """
                    cd ${APP_DIR}
                    docker compose -f docker-compose.ghcr.yml pull
                    docker compose -f docker-compose.ghcr.yml up -d
                """
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
            sh 'docker logout ghcr.io || true'
        }
    }
}
