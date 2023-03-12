# mlsSurvey
Default spring boot profile, for now, is dev.
Add -Dspring.profiles.active=dev in vm options while running from intellij
mvn spring-boot:run -Dspring-boot.run.profiles=dev from command line

Sample requests
To Upload questions
curl --location --request POST 'localhost:8080/survey/questions/upload' \
 --header 'Content-Type: application/json' \
 --data-raw '{
  "submission": {
      "question 1":["answer 1","answer 2"],"question 2":["answer 1", "answer 2"]
   }
 }
'

To submit a survey
curl --location --request POST 'localhost:8080/survey/submit' \
 --header 'Content-Type: application/json' \
 --data-raw '{
      "userId": "raga",
      "questionAnswerModel": {
      "submission": {
           "question 1":["answer 4"], "question 2":["answer 2"]
       }
    }
}'
To get survey result
curl --location --request GET 'localhost:8080/survey/result'




