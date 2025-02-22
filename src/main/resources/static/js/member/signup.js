
// DOM 요소
const usernameInput = document.getElementById('username');
const passwordInput = document.getElementById('password');
const confirmPasswordInput = document.getElementById('confirm-password');
const confirmPasswordError = document.getElementById('confirm-password-error');
const usernameError = document.getElementById('username-error')
const usernameAvailable = document.getElementById('username-available')

// 아이디 유효성 검사
const validateUsername = (username) => {
    const usernamePattern = /^[a-zA-Z0-9]{4,12}$/; // 영문, 숫자만 허용, 길이 4~12자
    return usernamePattern.test(username);
};

// 비밀번호 유효성 검사
const validatePassword = (password) => {
    const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;
    // 최소 8자 이상, 영문, 숫자, 특수문자 하나 이상 포함
    return passwordPattern.test(password);
};

// 아이디 중복 검사
document.getElementById('check-username').addEventListener('click', () => {
    usernameAvailable.textContent = '';
    usernameError.textContent = '';

    $.ajax({
        type: "POST",
        url: "/api/check-id/" + usernameInput.value,
        contentType: "application/json",
        success: function(res) {
            if (res) {
                usernameError.textContent = '아이디가 이미 존재합니다.';
            } else {
                usernameAvailable.textContent = '사용 가능한 아이디입니다.';
            }
        },
        error: function() {
            alert("프로그램 내부에 문제 발생")
        }
    });


});

// 비밀번호 확인 실시간 체크
confirmPasswordInput.addEventListener('input', () => {
    const password = passwordInput.value;
    const confirmPassword = confirmPasswordInput.value;

    if (password !== confirmPassword) {
        confirmPasswordError.textContent = '비밀번호가 일치하지 않습니다.';
    } else {
        confirmPasswordError.textContent = '';
    }
});

// 이름 유효성 검사 (공백 포함되지 않게)
const validateName = (name) => {
    return name.trim() !== '';
};


$(document).ready(function() {
    // 폼 제출 이벤트 처리
    $('#signup-form').on('submit', function(event) {
        event.preventDefault(); // 폼 기본 제출 막기

        // 값들 콘솔에 찍어서 확인
        const postData = {
            memberId: $('#username').val(),
            password: $('#password').val(),
            confirmPassword: $('#confirm-password').val(),
            name: $('#name').val(),
            type: $('input[name="user-type"]:checked').val()
        };

        $.ajax({
            type: "POST",
            url: "/api/create",
            data: JSON.stringify(postData),
            contentType: "application/json",
            success: function(res) {
                window.location.href = "/login";
            },
            error: function(err) {
                console.error("에러 발생:", err);
                alert("회원가입에 실패했습니다.");
            }
        });
    });
});