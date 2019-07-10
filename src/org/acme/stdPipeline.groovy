#!/bin/groovy
package org.acme;

def execute() {

  node {

    stage('Initialize') {
      checkout scm
      echo 'Loading pipeline definition'
      //yaml parser = new yaml()
      Map pipelineDefinition = parser.load(new File(pwd() + '/pipeline.yaml').text)
    }

    switch(pipelineDefinition.pipelineType) {
      case 'maven':
        // Instantiate and execute a Python pipeline
        new mavenPipeline(pipelineDefinition).executePipeline()
      case 'nodejs':
        // Instantiate and execute a NodeJS pipeline
        new nodeJSPipeline(pipelineDefinition).executePipeline()
    }

  }

}
