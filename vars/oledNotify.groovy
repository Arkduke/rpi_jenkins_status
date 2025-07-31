// vars/oledNotify.groovy

def call(Map config) {
    // Default values
    def status = config.status
    def jobName = env.JOB_NAME
    def result = config.result ?: '' // result is optional

    def payload
    if (status == 'building') {
        payload = '{"status": "building", "project": "' + jobName + '"}'
    } else {
        payload = '{"status": "finished", "result": "' + result + '"}'
    }
    
    // The webhook URL for your display script
    def webhookUrl = "http://localhost:5000/webhook"

    // Execute the curl command
    sh(script: "curl -X POST -H 'Content-Type: application/json' -d '${payload}' ${webhookUrl}", returnStatus: true)
}