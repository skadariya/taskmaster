# Taskmaster
It’s a task-tracking application with the same basic goal as Trello: allow users to keep track of tasks to be done and their status. While we’ll start today with a basic feature set, we will continue building out the capabilities of this application over time.

> A user should be able to make a GET request to /tasks and receive JSON data representing all of the tasks.
Each task should have a title, description, and status.

> A user should be able to make a POST request to /tasks with body parameters for title and description to add a new task.
All tasks should start with a status of Available.
The response to that request should contain the complete saved data for that task.

> A user should be able to make a PUT request to /tasks/{id}/state to advance the status of that task.
Tasks should advance from Available -> Assigned -> Accepted -> Finished.

> A user should be able to make a PUT request to /tasks/{id}/assign/{assignee} to assign a particular user to a task.
Changing the assignee should set the task’s state to Assigned.
This should work whether or not that assignee name is already in the database.

> A user should be able to make a PUT request to /tasks/{id}/state to advance the status of that task.
Tasks should advance from Available -> Assigned -> Accepted -> Finished

> A user should be able to make a POST request to /tasks with body parameters for title, description, and assignee to add a new task. A task should start with a status of Available if there is no assignee, and Assigned if there is an assignee.
The response to that request should contain the complete saved data for that task.
It should not matter whether or not that assignee is already in the database.

> User should be able to make a GET request to /users/{name}/tasks and receive JSON data representing all of the tasks assigned to that user.

> POST /tasks/{id}/images. This means it only needs to work for existing tasks, not as part of the initial creation of a task.

> GET /tasks/{id} : Fetching a single task  should also include the image URLs associated with that image.

# A link to deployed application.
> TaskMaster: http://task-dev.us-west-2.elasticbeanstalk.com

# Issues Faces

> Setting up the environment variable on local machine and setting it up in EB. 

> Dependecy issued . Current version of Dynamodb: 
  > compile 'com.github.derjust:spring-data-dynamodb:5.1.0'
	> compile 'com.amazonaws:aws-java-sdk-dynamodb:1.11.585'
