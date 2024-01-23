import React, { Component } from "react";
import axios from "axios";

class ImageUploadForm extends Component {
  state = {
    file: null,
  };

  handleFileChange = (event) => {
    this.setState({ file: event.target.files[0] });
  };

  handleImageUpload = (photo) => {
    const { file } = this.state;

    if (!file) {
      console.error("No file selected for upload");
      return;
    }

    const formData = new FormData();
    formData.append("file", file);
    formData.append("customName", photo);

    axios
      .post("http://localhost:8080/api/products/image", formData)
      .then((response) => {
        console.log("Image uploaded successfully", response.data);
      })
      .catch((error) => {
        console.error("Error uploading image", error);
      });
  };

  render() {
    return (
      <div>
        <h1>Upload an Image</h1>
        <label htmlFor="image">Select an image:</label>
        <input
          type="file"
          name="file"
          id="file"
          onChange={this.handleFileChange}
        />
      </div>
    );
  }
}

export default ImageUploadForm;
