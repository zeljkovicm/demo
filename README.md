# medicalTourismApp-back

This is a Spring Boot REST API in Java. App represents a basic version of medical tourism app - backend.  

Endpoints: 

UserController
•	GET /user/index
•	GET /user/get/user/by/{email}
•	GET /user/get/all/by/name
•	GET /user/get/user/reservations/by/email
•	GET /user/get/user/by/id
•	POST /user/create
•	POST /user/edit
•	POST /user/delete

AuthController
•	POST /auth/registration
•	POST /auth/login

TreatmentController
•	GET /treatment/get/all/treatments
•	GET /treatment/get/treatment/by/id
•	GET /treatment/get/treatment/category/by/id
•	POST /treatment/create
•	POST /treatment/edit
•	POST /treatment/delete

ReservationController
•	GET /reservation/get/reservation/by/id
•	GET /reservation/get/reservation/treatment/by/id
•	POST /reservation/create
•	POST /reservation/edit
•	POST /reservation/delete

PaymentController
•	POST /payment/save
•	POST /payment/delete
