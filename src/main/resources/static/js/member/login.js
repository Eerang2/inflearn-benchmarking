
$(document).ready(function() {
    // 폼 제출 이벤트 처리
    $('#login-form').on('submit', function(event) {
        event.preventDefault(); // 폼 기본 제출 막기

        // 값들 콘솔에 찍어서 확인
        const postData = {
            memberId: $('#login-id').val(),
            password: $('#login-password').val(),
            type: $('input[name="user-type"]:checked').val(),
        };

        $.ajax({
            type: "POST",
            url: "/api/member/login",
            data: JSON.stringify(postData),
            contentType: "application/json",
            success: function() {
                window.location.href = "/";
            },
            error: function(err) {
                console.error("에러 발생:", err);
                alert("로그인에 실패했습니다.");
            }
        });
    });
});