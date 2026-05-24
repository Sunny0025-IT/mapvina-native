include(FetchContent)

FetchContent_Declare(
    tinyobjloader
    GIT_REPOSITORY "https://github.com/tinyobjloader/tinyobjloader.git"
    GIT_TAG 5a2342f3b8ed1035bdd1822e5d71e363f1f825ea #release
    GIT_SHALLOW FALSE
    GIT_PROGRESS TRUE
)

FetchContent_MakeAvailable(tinyobjloader)

set_target_properties(
    tinyobjloader
    PROPERTIES
        INTERFACE_MAPVINA_NAME "tinyobjloader"
        INTERFACE_MAPVINA_URL "https://github.com/tinyobjloader/tinyobjloader.git"
        INTERFACE_MAPVINA_LICENSE ${CMAKE_CURRENT_LIST_DIR}/tinyobjloader/LICENSE
)
