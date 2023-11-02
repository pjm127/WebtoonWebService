
import React, { useEffect, useState } from 'react'
import "../globals.css"

export default function BackgroundPage() {
  // const [hasWindow, setHasWindow] = useState(false);
  // useEffect(() => {
  //     setHasWindow(true);
  // }, [hasWindow]);

  return (
      <div className = "z-[-1] absolute w-[100vw] h-[100vh] text-[0] top-[0px]">
          {/* {hasWindow && ( */}
              <video
                  autoPlay={true}
                  muted={true}
                  loop={true}
                  style={{ width: "100%", height: "100%" }}
                  src={require("../../../public/videos/webBackground.mp4")}
                  className = "mb-[-1] object-fill"
              />
          {/* )} */}
      </div>
  );
}
