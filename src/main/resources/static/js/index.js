$(document).ready(function () {
    // 0원 강의 API 호출
    $.ajax({
        url: '/api/recommend/free-backend-lectures', // 첫 번째 API 호출
        method: 'GET',
        success: function (data) {
            const freeCoursesContainer = $('#freeCourses'); // 데이터를 삽입할 영역
            data.lectures.forEach(lecture => {
                const courseCard = `
                    <div class="course-card">
                        <img src="${lecture.imageUrl}" alt="${lecture.title}">
                        <div class="details">
                            <h3>${lecture.title}</h3>
                            <p>${lecture.price}원</p>
                        </div>
                        <div class="hover-content">
                            <h4>${lecture.title}</h4>
                            <p>${lecture.description}</p>
                        </div>
                    </div>
                `;
                freeCoursesContainer.append(courseCard);
            });
        },
        error: function () {
            console.error('Failed to load free backend lectures.');
        }
    });

    // 추천 강의 API 호출
    $.ajax({
        url: '/api/recommend/new-lectures', // 두 번째 API 호출
        method: 'GET',
        success: function (data) {
            const recommendedCoursesContainer = $('#recommendedCourses'); // 데이터를 삽입할 영역
            data.lectures.forEach(lecture => {
                const courseCard = `
                    <div class="course-card">
                        <img src="${lecture.imageUrl}" alt="${lecture.title}">
                        <div class="details">
                            <h3>${lecture.title}</h3>
                            <p>${lecture.price}원</p>
                        </div>
                        <div class="hover-content">
                            <h4>${lecture.title}</h4>
                            <p>${lecture.description}</p>
                        </div>
                    </div>
                `;
                recommendedCoursesContainer.append(courseCard);
            });
        },
        error: function () {
            console.error('Failed to load recommended lectures.');
        }
    });
});
