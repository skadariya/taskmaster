# Taskmaster
It’s a task-tracking application with the same basic goal as Trello: allow users to keep track of tasks to be done and their status. While we’ll start today with a basic feature set, we will continue building out the capabilities of this application over time.
> A user should be able to make a GET request to /tasks and receive JSON data representing all of the tasks.
Each task should have a title, description, and status.

> A user should be able to make a POST request to /tasks with body parameters for title and description to add a new task.
All tasks should start with a status of Available.
The response to that request should contain the complete saved data for that task.

> A user should be able to make a PUT request to /tasks/{id}/state to advance the status of that task.
Tasks should advance from Available -> Assigned -> Accepted -> Finished.


# A link to deployed application.
[TaskMaster](http://task-dev.us-west-2.elasticbeanstalk.com/)

# Issues Faces
> Setting up the environment variable on local machine and setting it up in EB. 
** Working on solution
