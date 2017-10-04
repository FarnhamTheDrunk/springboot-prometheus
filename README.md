# springboot-prometheus
first test for springboot and prometheus

@First:
download Prometheus monitoring for your running OS

https://prometheus.io/download/
 
@Second 
add these line to the yml

  - job_name: 'spring-app'

    scrape_interval: 5s
    metrics_path: '/prometheus'
    static_configs:
      - targets: ['localhost:8080']


thats all
