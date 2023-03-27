import * as THREE from "https://unpkg.com/browse/three@0.150.1/build/three.module.js";
import {GLTFLoader} from "https://unpkg.com/browse/three@0.150.1/examples/jsm/loaders/GLTFLoader.js";    // GLTF 로더 가져오기

// 렌더러
const renderer = new THREE.WebGLRenderer({antialias: true});
renderer.setSize(window.innerWidth, window.innerHeight);
document.body.appendChild(renderer.domElement);

// 씬
const scene = new THREE.Scene();

// 카메라
const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);
camera.position.set(0, 1, 2);
camera.lookAt(0, 0, 0)

let light = new THREE.DirectionalLight(0xffffff, 20); //조명 
scene.add(light);

let object;

// GLTF 로더
const gltfLoader = new GLTFLoader();
gltfLoader.load('../../3d_models/SheenChair.glb', function(gltf)
{
    object = gltf.scene;
    scene.add(gltf.scene);
});

function animate()
{
    requestAnimationFrame(animate);

    object.rotation.y += 0.01;

    renderer.render(scene, camera);
}
animate();