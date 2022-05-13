using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class asdf : MonoBehaviour
{
    // 컴포넌트 캐시처리를 위한 변수
    private CharacterController controller;
    private new Transform transform;
    private Animator animator;
    private new Camera camera;

    // 가상의 Plane에 레이캐스팅하기위한 변수
    private Plane plane;
    private Ray ray;
    private Vector3 hitPoint;

    // 이동속도
    public float moveSpeed = 10.0f;


    // Start is called before the first frame update
    void Start()
    {
        controller = GetComponent<CharacterController>(); // 컴포넌트할당
        transform = GetComponent<Transform>();
        animator = GetComponent<Animator>();
        camera = Camera.main;   // 카메라가져오기

        // 가상의 바닥을 기준으로 주인공의 위치를 생성
        plane = new Plane(transform.up, transform.position);    // 

    }

    // Update is called once per frame
    void Update()
    {
        Move();
        Turn();
    }

    float h => Input.GetAxis("Horizontal");
    float v => Input.GetAxis("Vertical");

    void Move()
    {
        // x축 이동
        Vector3 cameraForward = camera.transform.forward;
        // y축 이동
        Vector3 cameraRight = camera.transform.right;
        // x,y축 초기화
        cameraForward.y = 0.0f;
        cameraRight.y = 0.0f;

        // 이동할 방향벡터 계산
        Vector3 moveDir = (cameraForward * v) + (cameraRight * h);
        // x, y, z
        moveDir.Set(moveDir.x, 0.0f, moveDir.z);

        // 캐릭터 이동 처리 (방향 * 속도)
        controller.SimpleMove(moveDir * moveSpeed);
        // 캐릭터 애니메이션 처리
        float forward = Vector3.Dot(moveDir, transform.forward);
        float strafe = Vector3.Dot(moveDir, transform.right);
        animator.SetFloat("Forward", forward);
        animator.SetFloat("Strafe", strafe);

    }
    void Turn()
    {
        // 마우스의 2차원 좌푯값을 이용해 3차원 레이블을 생성
        ray = camera.ScreenPointToRay(Input.mousePosition);
        float enter = 0.0f;

        // 가상의 바닥에 Ray를 발사해 충돌 지점의 거리를 enter변수로 반환
        plane.Raycast(ray, out enter);
        // 가상의 바닥에 Ray가 충돌한 좌푯값을 추출
        hitPoint = ray.GetPoint(enter);

        // 회전해야 할 방향의 벡터를 계산
        Vector3 lookDir = hitPoint - transform.position;
        lookDir.y = 0;
        // 캐릭터의 회전값 지정
        transform.localRotation = Quaternion.LookRotation(lookDir);

    }
}