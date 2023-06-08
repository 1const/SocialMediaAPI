# SocialMediaAPI

### Auth
|     Method    | URL           |
| ------------- | ------------- |
|   POST             |  /social-media-api/v1/auth/registration |
| POST  | /social-media-api/v1/auth/login  |

### User
|     Method    | URL           |
| ------------- | ------------- |
|   GET          | /social-media-api/v1/users/me  |
|       GET    | /social-media-api/v1/users/{authorId}/posts  |

### Post
|     Method    | URL           |
| ------------- | ------------- |
|GET            |/social-media-api/v1/posts/{id}
|POST            |/social-media-api/v1/posts
|PUT | /social-media-api/v1/posts/{id}
|DELETE| /social-media-api/v1/posts/{id}

### Message
|     Method    | URL           |
| ------------- | ------------- |
|GET             | /social-media-api/v1/friends/{userId}/messages
|POST            |/social-media-api/v1/friends/{userId}/messages

### ActivityFeed
|     Method    | URL           |
| ------------- | ------------- |
|GET             | /social-media-api/v1/activity-feed?page={page}&size={size}

### Subscription
|     Method    | URL           |
| ------------- | ------------- |
|POST            |/social-media-api/v1/subs/{subscriberId}/change-status
|POST            |/social-media-api/v1/users/{publisherId}/change-subscription
