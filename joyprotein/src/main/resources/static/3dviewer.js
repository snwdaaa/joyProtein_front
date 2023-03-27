import * as THREE from 'three';
import {GLTFLoader} from 'gltfLoader';

// 렌더러
const renderer = new THREE.WebGLRenderer({antialias: true});
const container = document.getElementById('viewer')
let viewerOffset = -20;
renderer.setSize(container.scrollWidth + viewerOffset, container.scrollHeight + viewerOffset);
container.appendChild(renderer.domElement);

// 씬
const scene = new THREE.Scene();
scene.background = new THREE.Color(0x5c6a79);
scene.backgroundBlurriness = 0.5;

// 카메라
const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);
camera.position.set(0, 1, 1);
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