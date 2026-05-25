if(CMAKE_ANDROID_ARCH_ABI STREQUAL "arm64-v8a")
    set(_wgpu_lib_arch "aarch64-linux-android")
    set(_ndk_clang_prefix "aarch64-linux-android")
elseif(CMAKE_ANDROID_ARCH_ABI STREQUAL "armeabi-v7a")
    set(_wgpu_lib_arch "armv7-linux-androideabi")
    set(_ndk_clang_prefix "armv7a-linux-androideabi")
elseif(CMAKE_ANDROID_ARCH_ABI STREQUAL "x86_64")
    set(_wgpu_lib_arch "x86_64-linux-android")
    set(_ndk_clang_prefix "x86_64-linux-android")
elseif(CMAKE_ANDROID_ARCH_ABI STREQUAL "x86")
    set(_wgpu_lib_arch "i686-linux-android")
    set(_ndk_clang_prefix "i686-linux-android")
endif()
set(_wgpu_lib_name "libwgpu_native.a")

# Detect Android NDK host tag for macOS, Linux, or Windows host
if(CMAKE_HOST_SYSTEM_NAME STREQUAL "Darwin")
    set(_ndk_host_tag "darwin-x86_64")
elseif(CMAKE_HOST_SYSTEM_NAME STREQUAL "Linux")
    set(_ndk_host_tag "linux-x86_64")
elseif(CMAKE_HOST_SYSTEM_NAME STREQUAL "Windows")
    set(_ndk_host_tag "windows-x86_64")
endif()

macro(mln_wgpu_android_setup_cargo_env env_prefix_var)
    message(STATUS "ANDROID_NDK: ${ANDROID_NDK}")
    message(STATUS "CMAKE_ANDROID_NDK: ${CMAKE_ANDROID_NDK}")
    message(STATUS "CMAKE_SYSROOT: ${CMAKE_SYSROOT}")
    message(STATUS "CMAKE_SYSTEM_VERSION: ${CMAKE_SYSTEM_VERSION}")
    message(STATUS "ANDROID_PLATFORM: ${ANDROID_PLATFORM}")
    message(STATUS "ANDROID_PLATFORM_LEVEL: ${ANDROID_PLATFORM_LEVEL}")
    message(STATUS "ANDROID_NATIVE_API_LEVEL: ${ANDROID_NATIVE_API_LEVEL}")
    
    # Determine the API level dynamically
    if(ANDROID_PLATFORM MATCHES "android-([0-9]+)")
        set(_api_level "${CMAKE_MATCH_1}")
    elseif(ANDROID_PLATFORM_LEVEL)
        set(_api_level "${ANDROID_PLATFORM_LEVEL}")
    elseif(ANDROID_NATIVE_API_LEVEL)
        set(_api_level "${ANDROID_NATIVE_API_LEVEL}")
    else()
        set(_api_level "${CMAKE_SYSTEM_VERSION}")
    endif()
    message(STATUS "Resolved Android API level for Cargo linker: ${_api_level}")
    
    # Configure compiler and sysroot for bindgen to find standard library headers (e.g. math.h)
    set(_ndk_sysroot "${ANDROID_NDK}/toolchains/llvm/prebuilt/${_ndk_host_tag}/sysroot")
    set(_ndk_clang "${ANDROID_NDK}/toolchains/llvm/prebuilt/${_ndk_host_tag}/bin/clang")
    set(_ndk_linker "${ANDROID_NDK}/toolchains/llvm/prebuilt/${_ndk_host_tag}/bin/${_ndk_clang_prefix}${_api_level}-clang")
    
    message(STATUS "_ndk_sysroot: ${_ndk_sysroot}")
    message(STATUS "_ndk_clang: ${_ndk_clang}")
    message(STATUS "_ndk_linker: ${_ndk_linker}")
    
    # Generate upper-case target name for cargo linker env var
    string(TOUPPER "${_wgpu_lib_arch}" _wgpu_lib_arch_upper)
    string(REPLACE "-" "_" _wgpu_lib_arch_upper "${_wgpu_lib_arch_upper}")
    
    set(${env_prefix_var} ${CMAKE_COMMAND} -E env 
        "CLANG_PATH=${_ndk_clang}"
        "BINDGEN_EXTRA_CLANG_ARGS=--target=${_wgpu_lib_arch} -isysroot ${_ndk_sysroot}"
        "CARGO_TARGET_${_wgpu_lib_arch_upper}_LINKER=${_ndk_linker}"
    )
endmacro()

macro(mln_wgpu_android_link_library target)
    find_package(Threads REQUIRED)
    target_link_libraries(${target} INTERFACE
        Threads::Threads
        ${CMAKE_DL_LIBS}
        log
        android
    )
endmacro()
