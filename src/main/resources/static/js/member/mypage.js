document.addEventListener("DOMContentLoaded", () => {
    const sidebarLinks = document.querySelectorAll(".sidebar ul li");
    const sections = document.querySelectorAll(".content .section");

    sidebarLinks.forEach(link => {
        link.addEventListener("click", () => {
            const sectionId = link.getAttribute("data-section");

            // 모든 섹션 숨기기
            sections.forEach(section => {
                section.style.display = "none";
            });

            // 선택한 섹션 표시
            const activeSection = document.getElementById(sectionId);
            activeSection.style.display = "block";

            // 데이터 로드 및 업데이트
            loadSectionData(sectionId);
        });
    });

    // 기본적으로 첫 번째 섹션 표시
    const defaultSection = document.querySelector(".content .section");
    defaultSection.style.display = "block";
});
function loadSectionData(sectionId) {
    $.ajax({
        url: `/api/mypage/${sectionId}`,
        type: "POST",
        success: function(data) {
            const activeSection = document.getElementById(sectionId);
            const contentBody = activeSection.querySelector(".content-body");
            contentBody.innerHTML = ''; // 기존 내용을 비우고

            console.log(data.bannerPath)
            // 데이터가 존재하는 경우에만 처리
            if (data.html && data.html.length > 0) {
                data.html.forEach(item => {
                    let courseDiv = document.createElement('div');
                    courseDiv.classList.add('course-item');
                    courseDiv.innerHTML = `
                        <img src="${data.bannerPath}" alt="${data.bannerName}">
                        <h3>${data.title}</h3>
                        <p>Price: ${data.price}</p>
                    `;
                    contentBody.appendChild(courseDiv);
                });
            } else {
                // 데이터가 없는 경우 메시지 표시
                contentBody.innerHTML = '<p>No courses available</p>';
            }
        },
        error: function(xhr, status, error) {
            console.error("Error:", error);
        }
    });
}