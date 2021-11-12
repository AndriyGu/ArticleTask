
# Project launch:Linc to swagger
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config/

##############################################################################

#to create system user and admin
#or system-controller in swagger
http://localhost:8080/system/add
# email = "1_USER@email.com"
# password = "password"

##############################################################################

#to create custom user
#or registration-controller in swagger
http://localhost:8080/api/registration
#{
#"email": "string",
#"password": "string",
#"role": "string"
#}
# password must contain numbers, small and big letters



##############################################################################

### to use the endpoints, you need to transfer a JWT token
### Headers-> authorisation 

#To create an article
POST http://localhost:8080/api/article/addNewArticle
#Get all articles
http://localhost:8080/api/article/getArticles/{{currentPage}}/{{pageSize}}


#####################################################

#The endpoint returns count of published articles on daily bases for the 7 days
#Only admin can use
http://localhost:8080/api/admin/getCountArticles
