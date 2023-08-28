# The Nutmeg test app

## Setup

The project requires JDK 17, which can be set
via `Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JDK`.

## Feature

The implementation is based on the api pagination support via start and limit offsets.
This ensures we only load resources when user scrolls through the list as fetching
data involves multiple api calls. The `/users` call is made once and then `/albums` and `/photo`
is called as the user scrolls.

- This app shows list of `/albums` - title, username, photo name and url.
- To fetch first photo of each album I rely on `/photos?album{id}&_limit={1}`

## App

| Albums list success                 | Album page error/retry state            | Failed to get error                | RTL                         |
|-------------------------------------|-----------------------------------------|------------------------------------|-----------------------------|
| ![](images/albums_success_page.png) | ![](images/paged_error_retry_state.png) | ![](images/failed_to_get_user.png) | ![](images/success_rtl.png) |

## Tests

- AlbumsRepositoryTest
- PhotosRepositoryTest
- UsersRepositoryTest
- GetAlbumAndPhotoUseCaseTest
- GetUserInfoUseCaseTest
- AlbumDetailPagingSourceTest
- AlbumsViewModelTest
